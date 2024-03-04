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
import com.github.tommyettinger.gand.DirectedGraph;

import java.util.Collection;

/**
 * Kryo {@link Serializer} for gand {@link DirectedGraph}s.
 * You should register the vertex type when you register this as the serializer for DirectedGraph.
 */
public class DirectedGraphSerializer extends Serializer<DirectedGraph<?>> {

    public DirectedGraphSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final DirectedGraph<?> data) {
        Collection<?> vertices = data.getVertices();
        Collection<? extends Connection<?>> edges = data.internals().getConnections();
        int length = vertices.size();
        output.writeVarInt(length, true);
        System.out.println("Writing " + length + " vertices...");
        for(Object v : vertices) {
            if(v == null) System.out.println("NO NO NO BAD VERTEX");
            kryo.writeClassAndObject(output, v);
        }
        length = edges.size();
        output.writeVarInt(length, true);
        System.out.println("Writing " + length + " edges...");
        for(Connection<?> e : edges) {
            if(e == null) System.out.println("NO NO NO BAD EDGE");
            kryo.writeClassAndObject(output, e.getA());
            kryo.writeClassAndObject(output, e.getB());
            output.writeFloat(e.getWeight());
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public DirectedGraph<?> read(final Kryo kryo, final Input input, final Class<? extends DirectedGraph<?>> dataClass) {
        DirectedGraph<?> graph = new DirectedGraph<>();
        DirectedGraph raw = graph;
        int length = input.readVarInt(true);
        System.out.println("Attempting to read " + length + " vertices...");
        for (int i = 0; i < length; i++) {
            raw.addVertex(kryo.readClassAndObject(input));
        }
        length = input.readVarInt(true);
        System.out.println("Attempting to read " + length + " edges...");
        for (int i = 0; i < length; i++) {
            raw.addEdge(kryo.readClassAndObject(input), kryo.readClassAndObject(input), input.readFloat());
        }
        return graph;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public DirectedGraph<?> copy(Kryo kryo, DirectedGraph<?> original) {
        DirectedGraph<?> graph = new DirectedGraph<>();
        DirectedGraph raw = graph;
        Collection<?> vertices = graph.getVertices();
        for(Object v : vertices){
            raw.addVertex(kryo.copy(v));
        }
        Collection<? extends Connection<?>> edges = graph.internals().getConnections();
        for(Connection<?> e : edges){
            raw.addEdge(kryo.copy(e.getA()), kryo.copy(e.getB()), e.getWeight());
        }
        return graph;
    }
}