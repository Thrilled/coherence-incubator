/*
 * File: PublishRequestIdentifier.java
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

package com.oracle.coherence.patterns.messaging;

import com.oracle.coherence.common.identifiers.Identifier;
import com.oracle.coherence.patterns.messaging.entryprocessors.PublishMessageProcessor;
import com.tangosol.io.ExternalizableLite;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.ExternalizableHelper;
import com.tangosol.util.UUID;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * A {@link PublishRequestIdentifier} is a cluster unique identifier that identifies a request to
 * publish a {@link Message}.  It is used to prevent a {@link Message} from being published twice in the case
 * of a partition transfer.  The {@link PublishRequestIdentifier} is generated by the
 * {@link DefaultMessagingSession} and processed by the {@link PublishMessageProcessor}.
 * <p>
 * Copyright (c) 2010. All Rights Reserved. Oracle Corporation.<br>
 * Oracle is a registered trademark of Oracle Corporation and/or its affiliates.
 *
 * @author Paul Mackin
 */
@SuppressWarnings("serial")
public class PublishRequestIdentifier implements ExternalizableLite, PortableObject
{
    /**
     * The {@link UUID} of the publisher that is publishing the {@link Message}.
     */
    private Identifier publisherIdentifier;

    /**
     * The sequence number of the next {@link Message} to publish.
     */
    private long messageSequenceNumber;


    /**
     * Required for {@link ExternalizableLite} and {@link PortableObject}.
     */
    public PublishRequestIdentifier()
    {
    }


    /**
     * Constructor.
     *
     * @param publisherIdentifier publisher identifier
     * @param messageSequenceNumber message sequence number
     */
    public PublishRequestIdentifier(Identifier publisherIdentifier,
                                    long       messageSequenceNumber)
    {
        this.publisherIdentifier   = publisherIdentifier;
        this.messageSequenceNumber = messageSequenceNumber;
    }


    /**
     * Get the publisher identifier.
     *
     * @return publisher identifier
     */
    public Identifier getPublisherIdentifier()
    {
        return publisherIdentifier;
    }


    /**
     * Get the message sequence number.
     *
     * @return message sequence number
     */
    public long getMessageSequenceNumber()
    {
        return messageSequenceNumber;
    }


    /**
     * {@inheritDoc}
     */
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;

        result = prime * result + publisherIdentifier.hashCode();
        result = prime * result + (int) (messageSequenceNumber ^ (messageSequenceNumber >>> 32));

        return result;
    }


    /**
     * {@inheritDoc}
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        final PublishRequestIdentifier other = (PublishRequestIdentifier) obj;

        if (!this.publisherIdentifier.equals(other.publisherIdentifier))
        {
            return false;
        }

        if (messageSequenceNumber != other.messageSequenceNumber)
        {
            return false;
        }

        return true;
    }


    /**
     * {@inheritDoc}
     */
    public void readExternal(DataInput in) throws IOException
    {
        publisherIdentifier   = (Identifier) ExternalizableHelper.readObject(in);
        messageSequenceNumber = ExternalizableHelper.readLong(in);
    }


    /**
     * {@inheritDoc}
     */
    public void writeExternal(DataOutput out) throws IOException
    {
        ExternalizableHelper.writeObject(out, publisherIdentifier);
        ExternalizableHelper.writeLong(out, messageSequenceNumber);
    }


    /**
     * {@inheritDoc}
     */
    public void readExternal(PofReader reader) throws IOException
    {
        publisherIdentifier   = (Identifier) reader.readObject(0);
        messageSequenceNumber = reader.readLong(1);
    }


    /**
     * {@inheritDoc}
     */
    public void writeExternal(PofWriter writer) throws IOException
    {
        writer.writeObject(0, publisherIdentifier);
        writer.writeLong(1, messageSequenceNumber);
    }


    /**
     * Return a string version of the {@link PublishRequestIdentifier}s.
     *
     * @return message identifier string
     */
    public String toString()
    {
        return String.format("PublishRequestIdentifier{%s-%d}", publisherIdentifier.toString(), messageSequenceNumber);
    }
}
