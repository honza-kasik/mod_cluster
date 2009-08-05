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

/**
 * Determines how to wait for a Process
 */
public enum WaitHow
{
    /** Wait for the specified process to finish */
    WAIT(   0),
    /** Do not wait -- just see if it has finished */
    NOWAIT( 1);


    private int value;
    private WaitHow(int v)
    {
        value = v;
    }

    public int valueOf()
    {
        return value;
    }

    public static WaitHow valueOf(int value)
    {
        for (WaitHow e : values()) {
            if (e.value == value)
                return e;
        }
        throw new IllegalArgumentException("Invalid initializer: " + value);
    }

}