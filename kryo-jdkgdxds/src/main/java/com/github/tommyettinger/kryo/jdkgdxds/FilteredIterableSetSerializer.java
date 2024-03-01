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

package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.github.tommyettinger.ds.FilteredIterableSet;
import com.github.tommyettinger.function.ObjPredicate;
import com.github.tommyettinger.function.ObjToSameFunction;

import java.util.NoSuchElementException;

public class FilteredIterableSetSerializer extends CollectionSerializer<FilteredIterableSet<?, ?>> {
    public FilteredIterableSetSerializer() {
        super();
        setElementsCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, FilteredIterableSet<?, ?> collection) {
        ObjPredicate<?> filter = collection.getFilter();
        ObjToSameFunction<?> editor = collection.getEditor();
        if(filter == null || editor == null)
            throw new NoSuchElementException("A FilteredIterableSet must have a non-null filter and editor to be serialized.");
        if(kryo.getClassResolver().getRegistration(filter.getClass()) == null)
            kryo.register(filter.getClass());
        if(kryo.getClassResolver().getRegistration(editor.getClass()) == null)
            kryo.register(editor.getClass());
        kryo.writeClassAndObject(output, filter);
        kryo.writeClassAndObject(output, editor);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected FilteredIterableSet<?, ?> create(Kryo kryo, Input input, Class<? extends FilteredIterableSet<?, ?>> type, int size) {
        return new FilteredIterableSet((ObjPredicate<?>)kryo.readClassAndObject(input), (ObjToSameFunction<?>) kryo.readClassAndObject(input), size);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected FilteredIterableSet<?, ?> createCopy(Kryo kryo, FilteredIterableSet original) {
        return new FilteredIterableSet(original.getFilter(), original.getEditor(), original.size(), original.getLoadFactor());
    }
}
