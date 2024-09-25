package org.jankgg.slp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.jankgg.slp.events.*;
import org.jankgg.slp.exception.SlippiParsingException;
import org.jankgg.slp.util.SlippiByteBuffer;

import com.devsmart.ubjson.UBObject;
import com.devsmart.ubjson.UBReader;

public class SlippiGame {
  private SlippiMetadata metadata;
  private PayloadEvent eventPayloads;
  private GameStartEvent gameStartEvent;
  private GameEndEvent gameEndEvent;
  private List<PreFrameUpdateEvent> preFrameUpdateEvents = new ArrayList<>();
  private List<PostFrameUpdateEvent> postFrameUpdateEvents = new ArrayList<>();



  public SlippiGame(File file) throws IOException {
    UBReader ubReader = null;
    try {
      Instant start = Instant.now();
      InputStream in = new FileInputStream(file);

      // wrap fileinputstream in bufferedinputstream, literally increases
      // performance by several orders of magnitude
      // guessing at a reasonable buffer size, optimize later
      BufferedInputStream bufferedInputStream = new BufferedInputStream(in, 32768);

      ubReader = new UBReader(bufferedInputStream);
      UBObject slippiUBJsonObject = ubReader.read().asObject();
      Instant end = Instant.now();
      // System.out.println("UBJson read in: " + Duration.between(start,
      // end).toMillis() + "ms.");
      parseEvents(slippiUBJsonObject);
      start = Instant.now();
      this.setMetadata(new SlippiMetadata(slippiUBJsonObject.get("metadata").asObject()));
      end = Instant.now();
      // System.out.println("Slippimetadata parsed in: " +
      // Duration.between(start, end).toMillis() + "ms.");
    } finally {
      if (ubReader != null) {
        ubReader.close();
      }
    }
  }

  private void parseEvents(UBObject slippiUBJsonObject) throws IOException, SlippiParsingException {
    Instant start = Instant.now();
    byte[] raw = slippiUBJsonObject.get("raw").asByteArray();
    SlippiByteBuffer rawBuffer = SlippiByteBuffer.wrap(raw);
    int eventPayloadCode = rawBuffer.getUnsigned();
    // System.out.println("Payload command byte: " + String.format("0x%08X",
    // eventPayloadCode));

    eventPayloads = new PayloadEvent(rawBuffer);
    // System.out.println(eventPayloads.toString());

    int i = 0;
    while (rawBuffer.remaining() > 0) {
      byte cmdByte = rawBuffer.get();
      int cmdSize = eventPayloads.get(cmdByte);
      //System.out.println(String.format("0x%02X", cmdByte));
      if (cmdByte == GameStartEvent.code) {
        if (gameStartEvent == null) {
          gameStartEvent = new GameStartEvent(rawBuffer, cmdSize);
        } else {
          throw new SlippiParsingException("Multiple game start events encountered, only one event of this type expected");
        }
      }
      else if (cmdByte == GameEndEvent.code) {
        if (gameEndEvent == null) {
          gameEndEvent = new GameEndEvent(rawBuffer, cmdSize);
        } else {
          throw new SlippiParsingException("Multiple game end events encountered, only one event of this type expected");
        }
      }
      else if (cmdByte == PreFrameUpdateEvent.code) {
        //noop for now but will be useful for comparing inputs to game state to find common misinputs and such
        PreFrameUpdateEvent preFrameEvent = new PreFrameUpdateEvent(rawBuffer, cmdSize);
        preFrameUpdateEvents.add(preFrameEvent);
      }
      else if (cmdByte == PostFrameUpdateEvent.code) {
        PostFrameUpdateEvent postFrameEvent = new PostFrameUpdateEvent(rawBuffer, cmdSize);
        postFrameUpdateEvents.add(postFrameEvent);
      }
      else {
        rawBuffer.skip(cmdSize);
      }
      i++;
    }
    //System.out.println("Total events: " + i);
    Instant end = Instant.now();
    //System.out.println("Events parsed in in: " + Duration.between(start, end).toMillis() + "ms.");

  }

  public SlippiMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(SlippiMetadata metadata) {
    this.metadata = metadata;
  }

  public PayloadEvent getEventPayloads() {
    return eventPayloads;
  }

  public GameStartEvent getGameStartEvent() {
    return gameStartEvent;
  }
  public List<PreFrameUpdateEvent> getPreFrameUpdateEvents() {
    return preFrameUpdateEvents;
  }

}
