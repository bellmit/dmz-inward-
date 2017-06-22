package dmztest.NIOPerformanceTest;// $Id$

import java.nio.ByteBuffer;

public class CreateArrayBuffer
{
  static public void main( String args[] ) throws Exception {
    byte array[] = new byte[1024];

    ByteBuffer buffer = ByteBuffer.wrap( array );  // 包裝數組  ， 獲取ByteBuffer特性

    buffer.put( (byte)'a' );
    buffer.put( (byte)'b' );
    buffer.put( (byte)'c' );

    buffer.flip();

    System.out.println( (char)buffer.get() );
    System.out.println( (char)buffer.get() );
    System.out.println( (char)buffer.get() );
  }
}
