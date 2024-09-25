package org.jankgg.slp.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

//Decorator class that adds unsigned getters
public class SlippiByteBuffer implements Comparable<ByteBuffer> {

  private ByteBuffer byteBuffer;

  public SlippiByteBuffer(ByteBuffer byteBuffer) {
    this.byteBuffer = byteBuffer;
  }

  //unsigned functionality
  public long getUnsignedInt() {
    return byteBuffer.getInt() & 0xFFFFFFFFL;
  }
  
  public int getUnsignedShort() {
    return byteBuffer.getShort() & 0xFFFF;
  }
  
  public short getUnsigned() {
    return (short) (byteBuffer.get() & 0xFF);
  }
  
  //skip method for convenience
  public void skip(int n) {
	  byteBuffer.position(byteBuffer.position() + n);
  }

  // old stuff
  public SlippiByteBuffer slice() {
    return new SlippiByteBuffer(byteBuffer.slice());
  }

  public SlippiByteBuffer duplicate() {
    return new SlippiByteBuffer(byteBuffer.duplicate());
  }

  public SlippiByteBuffer asReadOnlyBuffer() {
    return new SlippiByteBuffer(byteBuffer.asReadOnlyBuffer());
  }

  public byte get() {
    return byteBuffer.get();
  }

  public SlippiByteBuffer put(byte b) {
    byteBuffer.put(b);
    return this;
  }

  public byte get(int index) {
    return byteBuffer.get(index);
  }

  public SlippiByteBuffer put(int index, byte b) {
    byteBuffer.put(index, b);
    return this;
  }

  public SlippiByteBuffer compact() {
    byteBuffer.compact();
    return this;
  }

  public boolean isDirect() {
    return byteBuffer.isDirect();
  }

  public char getChar() {
    return byteBuffer.getChar();
  }

  public SlippiByteBuffer putChar(char value) {
    byteBuffer.putChar(value);
    return this;
  }

  public char getChar(int index) {
    return byteBuffer.getChar(index);
  }

  public SlippiByteBuffer putChar(int index, char value) {
    byteBuffer.putChar(index, value);
    return this;
  }

  public CharBuffer asCharBuffer() {
    return byteBuffer.asCharBuffer();
  }

  public short getShort() {
    return byteBuffer.getShort();
  }

  public SlippiByteBuffer putShort(short value) {
    byteBuffer.putShort(value);
    return this;
  }

  public short getShort(int index) {
    return byteBuffer.getShort(index);
  }

  public SlippiByteBuffer putShort(int index, short value) {
    byteBuffer.putShort(index, value);
    return this;
  }

  public ShortBuffer asShortBuffer() {
    return byteBuffer.asShortBuffer();
  }

  public int getInt() {
    return byteBuffer.getInt();
  }

  public SlippiByteBuffer putInt(int value) {
    byteBuffer.putInt(value);
    return this;
  }

  public int getInt(int index) {
    return byteBuffer.getInt(index);
  }

  public SlippiByteBuffer putInt(int index, int value) {
    byteBuffer.putInt(index, value);
    return this;
  }

  public IntBuffer asIntBuffer() {
    return byteBuffer.asIntBuffer();
  }

  public long getLong() {
    return byteBuffer.getLong();
  }

  public SlippiByteBuffer putLong(long value) {
    byteBuffer.putLong(value);
    return this;
  }

  public long getLong(int index) {
    return byteBuffer.getLong(index);
  }

  public SlippiByteBuffer putLong(int index, long value) {
    byteBuffer.putLong(index, value);
    return this;
  }

  public LongBuffer asLongBuffer() {
    return byteBuffer.asLongBuffer();
  }

  public float getFloat() {
    return byteBuffer.getFloat();
  }

  public SlippiByteBuffer putFloat(float value) {
    byteBuffer.putFloat(value);
    return this;
  }

  public float getFloat(int index) {
    return byteBuffer.getFloat(index);
  }

  public SlippiByteBuffer putFloat(int index, float value) {
    byteBuffer.putFloat(index, value);
    return this;
  }

  public FloatBuffer asFloatBuffer() {
    return byteBuffer.asFloatBuffer();
  }

  public double getDouble() {
    return byteBuffer.getDouble();
  }

  public SlippiByteBuffer putDouble(double value) {
    byteBuffer.putDouble(value);
    return this;
  }

  public double getDouble(int index) {
    return byteBuffer.getDouble(index);
  }

  public SlippiByteBuffer putDouble(int index, double value) {
    byteBuffer.putDouble(index, value);
    return this;
  }

  public DoubleBuffer asDoubleBuffer() {
    return byteBuffer.asDoubleBuffer();
  }

  public int compareTo(ByteBuffer o) {
    return byteBuffer.compareTo(o);
  }
  
  //inherited from buffer 
  //capacity, clear, flip, hasRemaining, isReadOnly, limit, limit, mark, position, position, remaining, reset, rewind
  
  public int capacity() {
    return byteBuffer.capacity();
  }
  
  public void clear() {
    byteBuffer.clear();
  }
  
  public SlippiByteBuffer flip() {
    byteBuffer.flip();
    return this;
  }
  
  public boolean hasRemaining() {
    return byteBuffer.hasRemaining();
  }
  
  public boolean isReadOnly() {
    return byteBuffer.isReadOnly();
  }
  
  public int limit() {
    return byteBuffer.limit();
  }
  
  public SlippiByteBuffer limit(int newLimit) {
    byteBuffer.limit(newLimit);
    return this;
  }
  
  public SlippiByteBuffer mark() {
    byteBuffer.mark();
    return this;
  }
  
  public int position() {
    return byteBuffer.position();
  }
  
  public SlippiByteBuffer position(int newPosition) {
    byteBuffer.position(newPosition);
    return this;
  }
  
  public int remaining() {
    return byteBuffer.remaining();
  }
  
  public SlippiByteBuffer reset() {
    byteBuffer.reset();
    return this;
  }
  
  public SlippiByteBuffer rewind() {
    byteBuffer.rewind();
    return this;
  }
  
  //statics
  public static SlippiByteBuffer allocate(int capacity) {
    return new SlippiByteBuffer(ByteBuffer.allocate(capacity));
  }
  
  public static SlippiByteBuffer allocateDirect(int capacity) {
    return new SlippiByteBuffer(ByteBuffer.allocateDirect(capacity));
  }
  
  public static SlippiByteBuffer wrap(byte[] array) {
    return new SlippiByteBuffer(ByteBuffer.wrap(array));
  }
  
  public static SlippiByteBuffer wrap(byte[] array, int offset, int length) {
    return new SlippiByteBuffer(ByteBuffer.wrap(array, offset, length));
  }

}