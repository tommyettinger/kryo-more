package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.github.tommyettinger.ds.Junction;
import com.github.tommyettinger.ds.ObjectList;

/**
 * Not actually a Serializer, this just has code to register the needed classes for Junction to be serializable.
 */
public class JunctionSupport {
    /**
     * Given a Kryo instance to register classes on, this registers (in order):
     * <ol>
     *     <li>{@link ObjectList}</li>
     *     <li>{@link Junction}</li>
     *     <li>{@link Junction.Any}</li>
     *     <li>{@link Junction.All}</li>
     *     <li>{@link Junction.One}</li>
     *     <li>{@link Junction.Not}</li>
     *     <li>{@link Junction.Leaf}</li>
     * </ol>
     * This means if you call this on a server, it also must be called at the same point in registration order as you
     * call it on the client-side.
     * @param kryo a non-null Kryo instance
     */
    public static void registerJunction(Kryo kryo) {
        kryo.register(ObjectList.class, new ObjectListSerializer());
        kryo.register(Junction.class);
        kryo.register(Junction.Any.class);
        kryo.register(Junction.All.class);
        kryo.register(Junction.One.class);
        kryo.register(Junction.Not.class);
        kryo.register(Junction.Leaf.class);

    }
}
