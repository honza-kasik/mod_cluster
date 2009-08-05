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
 * NetworkAdapter Test.
 *
 */
public class NetAdapterTest extends TestCase
{

    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(NetAdapterTest.class);
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

    public void testNetAdapter()
        throws Exception
    {
        int lb = 0;
        int lo = 0;
        Network net = new Network();
        assertNotNull(net.HostName);
        for (NetworkAdapter a : NetworkAdapter.getAdapters()) {
            if (a.Type == NetworkAdapterType.SOFTWARE_LOOPBACK) {
                lb++;
                if (a.Name.equals("lo")) {
                    lo++;
                }
            }
        }
        assertEquals(1, lo);

    }

}