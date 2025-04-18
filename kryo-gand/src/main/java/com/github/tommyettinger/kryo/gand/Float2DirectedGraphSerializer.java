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
import com.github.tommyettinger.gand.Float2DirectedGraph;
import com.github.tommyettinger.gdcrux.PointF2;

import java.util.Collection;

/**
 * Kryo {@link Serializer} for gand {@link Float2DirectedGraph}s.
 * You must have {@link PointF2} registered to use this as the serializer for Float2DirectedGraph,
 * such as with {@link com.github.tommyettinger.kryo.gdcrux.PointF2Serializer}.
 */
public class Float2DirectedGraphSerializer extends Serializer<Float2DirectedGraph> {

    public Float2DirectedGraphSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Float2DirectedGraph data) {
        Collection<PointF2> vertices = data.getVertices();
        Collection<? extends Connection<PointF2>> edges = data.internals().getConnections();
        int length = vertices.size();
        output.writeInt(length, true);
        for(PointF2 v : vertices) {
            kryo.writeObject(output, v);
        }
        length = edges.size();
        output.writeInt(length, true);
        for(Connection<PointF2> e : edges) {
            kryo.writeObject(output, e.getA());
            kryo.writeObject(output, e.getB());
            output.writeFloat(e.getWeight());
        }
    }

    @Override
    public Float2DirectedGraph read(final Kryo kryo, final Input input, final Class<? extends Float2DirectedGraph> dataClass) {
        Float2DirectedGraph graph = new Float2DirectedGraph();
        int length = input.readInt(true);
        for (int i = 0; i < length; i++) {
            graph.addVertex(kryo.readObject(input, PointF2.class));
        }
        length = input.readInt(true);
        for (int i = 0; i < length; i++) {
            graph.addEdge(kryo.readObject(input, PointF2.class), kryo.readObject(input, PointF2.class), input.readFloat());
        }
        return graph;
    }

    @Override
    public Float2DirectedGraph copy(Kryo kryo, Float2DirectedGraph original) {
        Float2DirectedGraph graph = new Float2DirectedGraph();
        Collection<PointF2> vertices = graph.getVertices();
        for(PointF2 v : vertices){
            graph.addVertex(kryo.copy(v));
        }
        Collection<? extends Connection<PointF2>> edges = graph.internals().getConnections();
        for(Connection<PointF2> e : edges){
            graph.addEdge(kryo.copy(e.getA()), kryo.copy(e.getB()), e.getWeight());
        }
        return graph;
    }
}