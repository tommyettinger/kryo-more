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
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import space.earlygrey.simplegraphs.Graph;

/**
 * Kryo {@link Serializer} for simple-graphs {@link Graph}s; this class is not currently usable.
 */
public class GraphSerializer<V> extends Serializer<Graph<V>> {

    public GraphSerializer() {
        setImmutable(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Graph<V> data) {
        // no-op for now
        //output.writeString(data.serializeToString());
    }

    @Override
    public Graph<V> read(final Kryo kryo, final Input input, final Class<? extends Graph<V>> dataClass) {
        // incorrect for now
        return null;
//        return Pattern.deserializeFromString(input.readString());
    }
}