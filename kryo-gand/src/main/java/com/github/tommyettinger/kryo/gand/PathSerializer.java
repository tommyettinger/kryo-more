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
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.github.tommyettinger.gand.Path;

public class PathSerializer extends CollectionSerializer<Path<?>> {
    public PathSerializer() {
        super();
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, Path<?> collection) {
        output.writeFloat(collection.getLength());
    }

    @Override
    protected Path<?> create(Kryo kryo, Input input, Class<? extends Path<?>> type, int size) {
        Path<?> path = new Path<>(size);
        path.setLength(input.readFloat());
        return path;
    }

    @Override
    protected Path<?> createCopy(Kryo kryo, Path<?> original) {
        return new Path<>(original.size());
    }
}
