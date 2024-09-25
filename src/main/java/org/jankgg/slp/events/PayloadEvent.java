package org.jankgg.slp.events;

import java.io.IOException;
import java.util.HashMap;

import org.jankgg.slp.util.SlippiByteBuffer;

public class PayloadEvent extends HashMap<Byte, Integer> {

  /**
   * 
   */
  private static final long serialVersionUID = 8075246939352691611L;
  public static final byte code = 0x35;

  public PayloadEvent(SlippiByteBuffer rawBuffer) throws IOException {
    // payloadsize includes the payload command byte for god knows what reason
    int eventPayloadSize = rawBuffer.getUnsigned() - 1;

    int numberOfEvents = eventPayloadSize / 3;

    // parse event sizes
    for (int i = 0; i < numberOfEvents; i++) {
      // get command byte
      byte cmdByte = rawBuffer.get();
      this.put(cmdByte, rawBuffer.getUnsignedShort());
    }
  }

  @Override
  public String toString() {
    String hexifiedString = "";
    for (Entry<Byte, Integer> entry : this.entrySet()) {
      hexifiedString += String.format("0x%02X", entry.getKey()) + "=" + entry.getValue()
          + " bytes, ";
    }
    return hexifiedString.substring(0, hexifiedString.length() - 2);
  }
}
