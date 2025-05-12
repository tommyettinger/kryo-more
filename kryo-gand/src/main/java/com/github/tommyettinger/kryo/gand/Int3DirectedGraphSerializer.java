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
import com.github.tommyettinger.gand.Int3DirectedGraph;
import com.github.tommyettinger.gdcrux.PointI3;

import java.util.Collection;

/**
 * Kryo {@link Serializer} for gand {@link Int3DirectedGraph}s.
 * You must have {@link PointI3} registered to use this as the serializer for Int3DirectedGraph,
 * such as with {@link com.github.tommyettinger.kryo.gdcrux.PointI3Serializer}.
 */
public class Int3DirectedGraphSerializer extends Serializer<Int3DirectedGraph> {
    public Int3DirectedGraphSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Int3DirectedGraph data) {
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
    public Int3DirectedGraph read(final Kryo kryo, final Input input, final Class<? extends Int3DirectedGraph> dataClass) {
        Int3DirectedGraph graph = new Int3DirectedGraph();
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
    public Int3DirectedGraph copy(Kryo kryo, Int3DirectedGraph original) {
        Int3DirectedGraph graph = new Int3DirectedGraph();
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
