/*
 * File: DistributableCacheInvocationRequestEvent.java
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * The contents of this file are subject to the terms and conditions of 
 * the Common Development and Distribution License 1.0 (the "License").
 *
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the License by consulting the LICENSE.txt file
 * distributed with this file, or by consulting https://oss.oracle.com/licenses/CDDL
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file LICENSE.txt.
 *
 * MODIFICATIONS:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 */

package com.oracle.coherence.patterns.eventdistribution.events;

import com.oracle.coherence.common.events.Event;
import com.tangosol.io.ExternalizableLite;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.NamedCache;
import com.tangosol.util.InvocableMap.EntryProcessor;

/**
 * A {@link DistributableCacheInvocationRequestEvent} represents a request to invoke
 * an {@link EntryProcessor} on a cache.
 * <p>
 * Copyright (c) 2011. All Rights Reserved. Oracle Corporation.<br>
 * Oracle is a registered trademark of Oracle Corporation and/or its affiliates.
 *
 * @author Brian Oliver
 */
public interface DistributableCacheInvocationRequestEvent extends Event, PortableObject, ExternalizableLite
{
    /**
     * Requests the invocation be performed using the provided {@link NamedCache}.
     *
     * @param namedCache The {@link NamedCache} on which to perform the invocation request.
     */
    public abstract void invoke(NamedCache namedCache);
}
