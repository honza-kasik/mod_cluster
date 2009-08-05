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
 * How to kill the process.
 */
public enum KillConditions
{
    /** Process is never sent any signals */
    KILL_NEVER(         0),
    /** Process is sent SIGKILL on Pool cleanup */
    KILL_ALWAYS(        1),
    /** Send SIGTERM, wait 3 seconds, SIGKILL */
    KILL_AFTER_TIMEOUT( 2),
    /** Wait forever for the process to complete */
    JUST_WAIT(          3),
    /** Send SIGTERM and then wait */
    KILL_ONLY_ONCE(     4);


    private int value;
    private KillConditions(int v)
    {
        value = v;
    }

    public int valueOf()
    {
        return value;
    }

    public static KillConditions valueOf(int value)
    {
        for (KillConditions e : values()) {
            if (e.value == value)
                return e;
        }
        throw new IllegalArgumentException("Invalid initializer: " + value);
    }

}