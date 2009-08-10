/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.modcluster.load.metric.impl;

import javax.management.JMException;
import javax.management.MalformedObjectNameException;

import org.jboss.modcluster.load.metric.DeterministicLoadState;
import org.jboss.modcluster.load.metric.LoadMetric;

/**
 * {@link LoadMetric} implementation that returns the incoming bandwidth in KB.
 * @author Paul Ferraro
 */
public class ReceiveTrafficLoadMetric extends MBeanAttributeLoadMetric
{
   public static final String DEFAULT_ATTRIBUTE = "bytesReceived";

   private final DeterministicLoadState state;
   
   public ReceiveTrafficLoadMetric() throws MalformedObjectNameException
   {
      this(new RequestProcessorLoadMetricSource());
   }
   
   public ReceiveTrafficLoadMetric(DeterministicLoadState state) throws MalformedObjectNameException
   {
      this(new RequestProcessorLoadMetricSource(), state);
   }
   
   public ReceiveTrafficLoadMetric(RequestProcessorLoadMetricSource source)
   {
      this(source, DEFAULT_ATTRIBUTE);
   }
   
   public ReceiveTrafficLoadMetric(RequestProcessorLoadMetricSource source, DeterministicLoadState state)
   {
      this(source, DEFAULT_ATTRIBUTE, state);
   }
   
   public ReceiveTrafficLoadMetric(RequestProcessorLoadMetricSource source, String attribute)
   {
      this(source, attribute, new DeterministicLoadStateImpl());
   }
   
   public ReceiveTrafficLoadMetric(RequestProcessorLoadMetricSource source, String attribute, DeterministicLoadState state)
   {
      super(source, attribute);
      
      this.state = state;
   }
   
   /**
    * @{inheritDoc}
    * @see org.jboss.modcluster.load.metric.impl.MBeanAttributeLoadMetric#getLoad(org.jboss.modcluster.load.metric.impl.MBeanQueryLoadContext)
    */
   @Override
   public double getLoad(MBeanQueryLoadContext context) throws JMException
   {
      return this.state.delta(super.getLoad(context) / 1000);
   }
}