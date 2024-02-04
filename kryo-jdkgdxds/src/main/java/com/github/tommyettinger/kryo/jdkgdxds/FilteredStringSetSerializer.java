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
import com.github.tommyettinger.ds.CharFilter;
import com.github.tommyettinger.ds.FilteredStringSet;
import com.github.tommyettinger.ds.Utilities;

import java.util.NoSuchElementException;

public class FilteredStringSetSerializer extends CollectionSerializer<FilteredStringSet> {
    public FilteredStringSetSerializer() {
        super();
        setElementsCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, FilteredStringSet collection) {
        CharFilter fil = collection.getFilter();
        if(fil == null)
            throw new NoSuchElementException("A FilteredStringSet must have a non-null filter to be serialized.");
        output.writeString(fil.getName());
    }

    @Override
    protected FilteredStringSet create(Kryo kryo, Input input, Class<? extends FilteredStringSet> type, int size) {
        return new FilteredStringSet(CharFilter.get(input.readString()), size, Utilities.getDefaultLoadFactor());
    }

    @Override
    protected FilteredStringSet createCopy(Kryo kryo, FilteredStringSet original) {
        return new FilteredStringSet(original.getFilter(), original.size(), original.getLoadFactor());
    }
}
