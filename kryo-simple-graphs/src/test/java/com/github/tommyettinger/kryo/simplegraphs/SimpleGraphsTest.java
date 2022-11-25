/*
 * Copyright (c) 2022 See AUTHORS file.
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

package com.github.tommyettinger.kryo.simplegraphs;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Assert;
import org.junit.Test;
import space.earlygrey.simplegraphs.Graph;
import space.earlygrey.simplegraphs.UndirectedGraph;

import java.io.ByteArrayOutputStream;

public class SimpleGraphsTest {
	/*
    @Test
    public void testPattern() {
        Kryo kryo = new Kryo();
        kryo.register(Pattern.class, new PatternSerializer());

        Pattern data = Pattern.compile("[a-z0-9_\\p{Sc}]+", REFlags.IGNORE_CASE | REFlags.UNICODE);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Pattern data2 = kryo.readObject(input, Pattern.class);
            Assert.assertEquals(data.matches("Meow€€€"), data2.matches("Meow€€€"));
            Assert.assertEquals(data.matches("Meow, baby, meow."), data2.matches("Meow, baby, meow."));
            Assert.assertEquals(data, data2);
        }
    }
	*/

    @Test
    public void testUndirectedGraph() {
        Kryo kryo = new Kryo();
        kryo.register(UndirectedGraph.class, new UndirectedGraphSerializer());
        kryo.register(Vector2.class);

        int n = 2;
        Graph<Vector2> data = Vector2.makeGridGraph(new UndirectedGraph<>(), n);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            UndirectedGraph data2 = kryo.readObject(input, UndirectedGraph.class);
            Assert.assertEquals(data.numberOfComponents(), data2.numberOfComponents());
            Assert.assertEquals(data.getEdgeCount(), data2.getEdgeCount());
            Assert.assertEquals(data.getVertices(), data2.getVertices());
            Assert.assertEquals(data.getEdges(), data2.getEdges());
        }

    }
}
