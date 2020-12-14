package com.dahan.gohan.option.command

import com.dahan.gohan.commandline.Option


/* ************************************************************************
 *
 * Copyright (C) 2020 dahan All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/*
 * Creates on 2020/12/12.
 */

/**
 * @author kevin
 */
class C_Clean extends Option
{

    C_Clean()
    {
        super("clean", "clean", false, "清空编译缓存")
        setOrder(1)
    }

    @Override
    void exec()
    {
    }

}