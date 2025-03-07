/*
 * Copyright (c) 2022-2024 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.tommyettinger.kryo.gand;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.gand.Connection;
import com.github.tommyettinger.gand.Int3UndirectedGraph;
import com.github.tommyettinger.gdcrux.PointI3;

import java.util.Collection;

/**
 * Kryo {@link Serializer} for gand {@link Int3UndirectedGraph}s.
 * You must have {@link PointI3} registered to use this as the serializer for Int3UndirectedGraph.
 * <br>
 * This currently doesn't work reliably because {@link com.github.tommyettinger.kryo.gand.points.PointI3Serializer}
 * doesn't work reliably. It looks like a bug in Kryo 5.x, but I can't be sure.
 */
public class Int3UndirectedGraphSerializer extends Serializer<Int3UndirectedGraph> {
    public Int3UndirectedGraphSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Int3UndirectedGraph data) {
        Collection<PointI3> vertices = data.getVertices();
        Collection<? extends Connection<PointI3>> edges = data.internals().getConnections();
        int length = vertices.size();
        output.writeInt(length, true);
        for(PointI3 v : vertices) {
            kryo.writeObject(output, v);
        }
        length = edges.size();
        output.writeInt(length, true);
        for(Connection<PointI3> e : edges) {
            kryo.writeObject(output, e.getA());
            kryo.writeObject(output, e.getB());
            output.writeFloat(e.getWeight());
        }
    }

    @Override
    public Int3UndirectedGraph read(final Kryo kryo, final Input input, final Class<? extends Int3UndirectedGraph> dataClass) {
        Int3UndirectedGraph graph = new Int3UndirectedGraph();
        int length = input.readInt(true);
        for (int i = 0; i < length; i++) {
            PointI3 item = kryo.readObject(input, PointI3.class);
            graph.addVertex(item);
        }
        length = input.readInt(true);
        for (int i = 0; i < length; i++) {
            graph.addEdge(kryo.readObject(input, PointI3.class), kryo.readObject(input, PointI3.class), input.readFloat());
        }
        return graph;
    }

    @Override
    public Int3UndirectedGraph copy(Kryo kryo, Int3UndirectedGraph original) {
        Int3UndirectedGraph graph = new Int3UndirectedGraph();
        Collection<PointI3> vertices = graph.getVertices();
        for(PointI3 v : vertices){
            graph.addVertex(kryo.copy(v));
        }
        Collection<? extends Connection<PointI3>> edges = graph.internals().getConnections();
        for(Connection<PointI3> e : edges){
            graph.addEdge(kryo.copy(e.getA()), kryo.copy(e.getB()), e.getWeight());
        }
        return graph;
    }
}
