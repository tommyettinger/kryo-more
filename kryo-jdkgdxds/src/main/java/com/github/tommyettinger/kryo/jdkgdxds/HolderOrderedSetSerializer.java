/*
 * Copyright (c) 2022-2023 See AUTHORS file.
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

package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.github.tommyettinger.ds.HolderOrderedSet;
import com.github.tommyettinger.function.ObjToObjFunction;

import java.util.NoSuchElementException;

public class HolderOrderedSetSerializer extends CollectionSerializer<HolderOrderedSet<?, ?>> {
    public HolderOrderedSetSerializer() {
        super();
        setElementsCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, HolderOrderedSet<?, ?> collection) {
        ObjToObjFunction<?, ?> ext = collection.getExtractor();
        if(ext == null)
            throw new NoSuchElementException("A HolderSet must have a non-null extractor to be serialized.");
        if(kryo.getClassResolver().getRegistration(ext.getClass()) == null)
            kryo.register(ext.getClass());
        kryo.writeClassAndObject(output, ext);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected HolderOrderedSet<?, ?> create(Kryo kryo, Input input, Class<? extends HolderOrderedSet<?, ?>> type, int size) {
        return new HolderOrderedSet<>((ObjToObjFunction)kryo.readClassAndObject(input), size);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    protected HolderOrderedSet<?, ?> createCopy(Kryo kryo, HolderOrderedSet<?, ?> original) {
        return new HolderOrderedSet<>(original.getExtractor(), original.size(), original.getLoadFactor());
    }
}
