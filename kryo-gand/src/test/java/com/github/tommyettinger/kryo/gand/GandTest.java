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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.crux.Point2;
import com.github.tommyettinger.crux.Point3;
import com.github.tommyettinger.gand.*;
import com.github.tommyettinger.gand.ds.ObjectOrderedSet;
import com.github.tommyettinger.gdcrux.*;
import com.github.tommyettinger.kryo.gand.points.PointF2Serializer;
import com.github.tommyettinger.kryo.gand.points.PointI2Serializer;
import com.github.tommyettinger.kryo.gand.points.PointI3FallbackSerializer;
import com.github.tommyettinger.kryo.gand.points.PointI3Serializer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.github.tommyettinger.gdcrux.PointMaker.pt;

public class GandTest {
    public static Graph<Vector2> makeGridGraph(Graph<Vector2> graph, int sideLength) {

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                Vector2 v = new Vector2(i, j);
                graph.addVertex(v);
            }
        }

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (i<sideLength-1) {
                    Vector2 v1 = new Vector2(i, j), v2 = new Vector2(i+1,j);
                    float dst = v1.dst(v2);
                    graph.addEdge(v1, v2, dst);
                    if (graph.isDirected()) graph.addEdge(v2, v1, dst);
                }
                if (j<sideLength-1) {
                    Vector2 v1 = new Vector2(i, j), v2 = new Vector2(i,j+1);
                    float dst = v1.dst(v2);
                    graph.addEdge(v1, v2, dst);
                    if (graph.isDirected()) graph.addEdge(v2, v1, dst);
                }
            }
        }

        return graph;
    }

    public static<V extends Point2<V>> Graph<V> makeGridGraph2D(Graph<V> graph, int sideLength, V basis) {
        MathUtils.random.setSeed(sideLength);
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if(MathUtils.randomBoolean(0.7f))
                    graph.addVertex(basis.cpy().set(i, j));
            }
        }

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (i<sideLength-1) {
                    V v1 = basis.cpy().set(i, j);
                    if(!graph.contains(v1)) continue;
                    V v2 = basis.cpy().set(i+1,j);
                    if(!graph.contains(v2)) continue;
                    float dst = v1.dst(v2);
                    graph.addEdge(v1, v2, dst);
                    if (graph.isDirected()) graph.addEdge(v2, v1, dst);
                }
                if (j<sideLength-1) {
                    V v1 = basis.cpy().set(i, j);
                    if(!graph.contains(v1)) continue;
                    V v2 = basis.cpy().set(i,j+1);
                    if(!graph.contains(v2)) continue;
                    float dst = v1.dst(v2);
                    graph.addEdge(v1, v2, dst);
                    if (graph.isDirected()) graph.addEdge(v2, v1, dst);
                }
            }
        }

        return graph;
    }

    public static<V extends Point3<V>> Graph<V> makeGridGraph3D(Graph<V> graph, int sideLength, V basis) {
        MathUtils.random.setSeed(sideLength);
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                for (int k = 0; k < sideLength; k++) {
                    if(MathUtils.randomBoolean(0.9f))
                        graph.addVertex(basis.cpy().set(i, j, k));
                }
            }
        }

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                for (int k = 0; k < sideLength; k++) {
                    if (i < sideLength - 1) {
                        V v1 = basis.cpy().set(i, j, k);
                        if(!graph.contains(v1)) continue;
                        V v2 = basis.cpy().set(i + 1, j, k);
                        if(!graph.contains(v2)) continue;
                        float dst = v1.dst(v2);
                        graph.addEdge(v1, v2, dst);
                        if (graph.isDirected()) graph.addEdge(v2, v1, dst);
                    }
                    if (j < sideLength - 1) {
                        V v1 = basis.cpy().set(i, j, k);
                        if(!graph.contains(v1)) continue;
                        V v2 = basis.cpy().set(i, j + 1, k);
                        if(!graph.contains(v2)) continue;
                        float dst = v1.dst(v2);
                        graph.addEdge(v1, v2, dst);
                        if (graph.isDirected()) graph.addEdge(v2, v1, dst);
                    }
                    if (k < sideLength - 1) {
                        V v1 = basis.cpy().set(i, j, k);
                        if(!graph.contains(v1)) continue;
                        V v2 = basis.cpy().set(i, j, k + 1);
                        if(!graph.contains(v2)) continue;
                        float dst = v1.dst(v2);
                        graph.addEdge(v1, v2, dst);
                        if (graph.isDirected()) graph.addEdge(v2, v1, dst);
                    }
                }
            }
        }
        System.out.println("Graph has " + graph.getVertices().size() + " vertices and " + graph.getEdgeCount() + " edges.");
        return graph;
    }

    @Test
    public void testUndirectedGraph() {
        Kryo kryo = new Kryo();
        kryo.register(UndirectedGraph.class, new UndirectedGraphSerializer());
        kryo.register(Vector2.class);

        int n = 5;
        Graph<Vector2> data = makeGridGraph(new UndirectedGraph<>(), n);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("UndirectedGraph byte length: " + bytes.length);
        try (Input input = new Input(bytes)) {
            UndirectedGraph<?> data2 = kryo.readObject(input, UndirectedGraph.class);
            Assert.assertEquals(data.numberOfComponents(), data2.numberOfComponents());
            Assert.assertEquals(data.getEdgeCount(), data2.getEdgeCount());
            Assert.assertEquals(new ArrayList<>(data.getVertices()), new ArrayList<>(data2.getVertices()));
            Assert.assertEquals(data.getEdges().stream().map(Object::toString).collect(Collectors.toList()),
                                data2.getEdges().stream().map(Object::toString).collect(Collectors.toList()));
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testDirectedGraph() {
        Kryo kryo = new Kryo();
        kryo.register(DirectedGraph.class, new DirectedGraphSerializer());
        kryo.register(Vector2.class);

        int n = 5;
        Graph<Vector2> data = makeGridGraph(new DirectedGraph<>(), n);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("DirectedGraph byte length: " + bytes.length);
        try (Input input = new Input(bytes)) {
            DirectedGraph<?> data2 = kryo.readObject(input, DirectedGraph.class);
            Assert.assertEquals(data.numberOfComponents(), data2.numberOfComponents());
            Assert.assertEquals(data.getEdgeCount(), data2.getEdgeCount());
            Assert.assertEquals(new ArrayList<>(data.getVertices()), new ArrayList<>(data2.getVertices()));
            Assert.assertEquals(data.getEdges().stream().map(Object::toString).collect(Collectors.toList()),
                                data2.getEdges().stream().map(Object::toString).collect(Collectors.toList()));
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    @Ignore("There appears to be a bug in Kryo 5.x that breaks PointI3 in particular.")
    public void testDirectedGraphAgain() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.register(DirectedGraph.class, new DirectedGraphSerializer());
        kryo.register(PointI3.class, new PointI3FallbackSerializer());

        int n = 5;
        DirectedGraph<PointI3> data = new DirectedGraph<>();
        makeGridGraph3D(data, n, new PointI3());
        System.out.println("Initial graph with length " + data.getVertices().size() + ", edge count " + data.getEdgeCount() + ": ");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("DirectedGraph byte length: " + bytes.length);
        try (Input input = new Input(bytes)) {
            DirectedGraph<?> data2 = kryo.readObject(input, DirectedGraph.class);
            System.out.println("Read back in with length " + data2.getVertices().size() + ", edge count " + data2.getEdgeCount() + ": ");
            Assert.assertEquals(data.numberOfComponents(), data2.numberOfComponents());
            Assert.assertEquals(data.getEdgeCount(), data2.getEdgeCount());
            Assert.assertEquals(new ArrayList<>(data.getVertices()), new ArrayList<>(data2.getVertices()));
            Assert.assertEquals(data.getEdges().stream().map(Object::toString).collect(Collectors.toList()),
                                data2.getEdges().stream().map(Object::toString).collect(Collectors.toList()));
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testInt2DirectedGraph() {
        Kryo kryo = new Kryo();
        kryo.register(Int2DirectedGraph.class, new Int2DirectedGraphSerializer());
        kryo.register(PointI2.class, new PointI2Serializer());

        int n = 5;
        Int2DirectedGraph data = new Int2DirectedGraph();
        makeGridGraph2D(data, n, new PointI2());
        System.out.println("Initial graph with length " + data.getVertices().size() + ": ");
        System.out.println(data.getVertices());
        System.out.println(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Int2DirectedGraph byte length: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Int2DirectedGraph data2 = kryo.readObject(input, Int2DirectedGraph.class);
            System.out.println("Read back in with length " + data2.getVertices().size() + ": ");
            System.out.println(data2.getVertices());
            System.out.println(data2);
            Assert.assertEquals(data.numberOfComponents(), data2.numberOfComponents());
            Assert.assertEquals(data.getEdgeCount(), data2.getEdgeCount());
            Assert.assertEquals(new ArrayList<>(data.getVertices()), new ArrayList<>(data2.getVertices()));
            Assert.assertEquals(data.getEdges().stream().map(Object::toString).collect(Collectors.toList()),
                                data2.getEdges().stream().map(Object::toString).collect(Collectors.toList()));
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFloat2DirectedGraph() {
        Kryo kryo = new Kryo();
        kryo.register(Float2DirectedGraph.class, new Float2DirectedGraphSerializer());
        kryo.register(PointF2.class, new PointF2Serializer());

        int n = 5;
        Graph<PointF2> data = makeGridGraph2D(new Float2DirectedGraph(), n, new PointF2());

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Float2DirectedGraph byte length: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Float2DirectedGraph data2 = kryo.readObject(input, Float2DirectedGraph.class);
            Assert.assertEquals(data.numberOfComponents(), data2.numberOfComponents());
            Assert.assertEquals(data.getEdgeCount(), data2.getEdgeCount());
            Assert.assertEquals(new ArrayList<>(data.getVertices()), new ArrayList<>(data2.getVertices()));
            Assert.assertEquals(data.getEdges().stream().map(Object::toString).collect(Collectors.toList()),
                                data2.getEdges().stream().map(Object::toString).collect(Collectors.toList()));
            Assert.assertEquals(data, data2);
        }
    }

    @Test
//    @Ignore("There appears to be a bug in Kryo 5.x that breaks PointI3 in particular.")
    public void testInt3DirectedGraph() {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(PointI3.class);
        kryo.register(Int3DirectedGraph.class, new Int3DirectedGraphSerializer());
//        kryo.register(java.lang.Object[].class);
//        kryo.register(ArrayList.class);
//        kryo.register(ObjectMap.class);
//        kryo.register(ObjectOrderedSet.class);
//        kryo.register(Connection.DirectedConnection.class);
//        kryo.register(Node.class);
//        kryo.register(Node[].class);
//        kryo.register(NodeMap.class);
//        kryo.register(NodeCollection.class);
//        kryo.register(VertexSet.class);
//        kryo.register(Int3DirectedGraph.class, new Int3DirectedGraphSerializer());

        int n = 5;
        Int3DirectedGraph data = new Int3DirectedGraph();
        makeGridGraph3D(data, n, new com.github.tommyettinger.gdcrux.PointI3());

        System.out.println("Initial graph with length " + data.getVertices().size() + ", edge count " + data.getEdgeCount() + ": ");
        System.out.println(data.getVertices());
        System.out.println(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        System.out.println("Int3DirectedGraph byte length: " + bytes.length);
        try (Input input = new Input(bytes)) {
            Int3DirectedGraph data2 = kryo.readObject(input, Int3DirectedGraph.class);

            System.out.println("Read back in with length " + data2.getVertices().size() + ", edge count " + data2.getEdgeCount() + ": ");
            System.out.println(data2.getVertices());
            System.out.println(data2);
            Assert.assertEquals(data.numberOfComponents(), data2.numberOfComponents());
            Assert.assertEquals(data.getEdgeCount(), data2.getEdgeCount());
            Assert.assertEquals(new ArrayList<>(data.getVertices()), new ArrayList<>(data2.getVertices()));
            Assert.assertEquals(data.getEdges().stream().map(Object::toString).collect(Collectors.toList()),
                    data2.getEdges().stream().map(Object::toString).collect(Collectors.toList()));
            Assert.assertEquals(data, data2);
        }
    }

    @Test
//    @Ignore("There appears to be a bug in Kryo 5.x that breaks PointI3 in particular.")
    public void testInt3List() {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(PointI3.class);
        kryo.register(ArrayList.class);
//        kryo.register(java.lang.Object[].class);
//        kryo.register(ArrayList.class);
//        kryo.register(ObjectMap.class);
//        kryo.register(ObjectOrderedSet.class);
//        kryo.register(Connection.DirectedConnection.class);
//        kryo.register(Node.class);
//        kryo.register(Node[].class);
//        kryo.register(NodeMap.class);
//        kryo.register(NodeCollection.class);
//        kryo.register(VertexSet.class);
//        kryo.register(Int3DirectedGraph.class, new Int3DirectedGraphSerializer());

        int n = 12; // passes with: int n = 11;
        Int3DirectedGraph data = new Int3DirectedGraph();
        makeGridGraph3D(data, n, new com.github.tommyettinger.gdcrux.PointI3());
        ArrayList<PointI3> list = new ArrayList<>(data.getVertices());
        System.out.println("List with length " + list.size() + ": ");
        System.out.println(list);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, list);
        byte[] bytes = output.toBytes();
        System.out.println("ArrayList byte length: " + bytes.length);
        try (Input input = new Input(bytes)) {
            ArrayList list2 = kryo.readObject(input, ArrayList.class);

            System.out.println("Read back in with length " + list2.size() + ": ");
            System.out.println(list2);
            Assert.assertEquals(list.size(), list2.size());
            Assert.assertEquals(list, list2);
        }
    }
    /*
Graph has 1211 vertices and 5550 edges.
List with length 1211:
// a really long line
ArrayList byte length: 3637
Read back in with length 1211:
// it's the same as the above long line
     */

    @Test
    public void testPath() {
        Kryo kryo = new Kryo();
        kryo.register(PointI2.class, new PointI2Serializer());
        kryo.register(Path.class, new PathSerializer());

        Path<PointI2> data = Path.with(pt(1, 1), pt(1, 2), pt(1, 3), pt(2, 3), pt(2, 4));
        data.setLength(4f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Path data2 = kryo.readObject(input, Path.class);
            Assert.assertEquals(data, data2);
        }
    }
}
