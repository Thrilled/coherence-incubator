/*
 * File: ActivePassiveListener.java
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * The contents of this file are subject to the terms and conditions of 
 * the Common Development and Distribution License 1.0 (the "License").
 *
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the License by consulting the LICENSE.txt file
 * distributed with this file, or by consulting
 * or https://oss.oracle.com/licenses/CDDL
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

package com.oracle.coherence.patterns.pushreplication.examples.activepassive;

import com.oracle.coherence.patterns.pushreplication.examples.ListenerHelper;

import com.tangosol.net.DefaultCacheServer;

/**
 * The {@link ActivePassiveListener} listens for events on a passive cache
 * that is being filled by push replication from a remote active (publishing) site.
 * <p>
 * Copyright (c) 2010. All Rights Reserved. Oracle Corporation.<br>
 * Oracle is a registered trademark of Oracle Corporation and/or its affiliates.
 *
 * @author Paul Mackin
 */
public class ActivePassiveListener
{
    /**
     * Main Application Entry Point
     *
     * @param args
     *
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException
    {
        // force all auto-start services to startup
        DefaultCacheServer.startDaemon();

        ListenerHelper listener = new ListenerHelper("passive-cache",    // local site cache name
                                                     "site2");           // this site name

        // Start the background listener
        listener.addListener();

        // System.out.println("waiting for listener to be done...");
        while (!listener.isDoneListening())
        {
            Thread.sleep(100);
        }

        System.out.println("success.");
    }
}
