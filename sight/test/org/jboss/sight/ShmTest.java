/*
 *  SIGHT - System information gathering hybrid tool
 *
 *  Copyright(c) 2007 Red Hat Middleware, LLC,
 *  and individual contributors as indicated by the @authors tag.
 *  See the copyright.txt in the distribution for a
 *  full listing of individual contributors.
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library in the file COPYING.LIB;
 *  if not, write to the Free Software Foundation, Inc.,
 *  59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 *
 */
package org.jboss.sight;

import java.nio.ByteBuffer;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Shared memory Test.
 *
 */
public class ShmTest extends TestCase
{

    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(ShmTest.class);
    }

    protected void setUp()
        throws Exception
    {
        Library.initialize("");
    }

    protected void tearDown()
        throws Exception
    {
        Library.shutdown();
    }

    public void testShm()
        throws Exception
    {
        int size = 64 * 1024;
        Shm shmc = new Shm("./SharedMemory.bin", size);
        ByteBuffer bb = shmc.getBytes();

        assertEquals(size, bb.capacity());
        bb.putInt(0xCAFEBABE);
        bb.putInt(0xDEADBEEF);

        /* Attach Shared memory */
        Shm shma = new Shm();

        int rv = shma.attach("./SharedMemory.bin");
        assertEquals(rv, Error.APR_SUCCESS);
        assertEquals(size, shma.size());

        ByteBuffer br = shma.getBytes();
        assertEquals(0xCAFEBABE, br.getInt());
        assertEquals(0xDEADBEEF, br.getInt());
    }

}