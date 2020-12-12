package com.dahan.gohan

import com.dahan.gohan.option.GohanOption
import com.dahan.gohan.option.command.C_Build
import com.dahan.gohan.option.command.C_Clean
import com.dahan.gohan.option.command.C_Debug
import com.dahan.gohan.option.command.C_Help
import com.dahan.gohan.option.command.C_Run
import com.dahan.gohan.reflect.ClassUtils
import org.apache.commons.cli.*

/* ************************************************************************
 *
 * Copyright (C) 2020 2B键盘 All rights reserved.
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
 * Creates on 2020/11/29.
 */

/**
 * 命令行解析
 * @author kevin
 */
class GohanMain
{

    private static Options options

    private static def commandArgs = [
            build: opt(C_Build.class),
            clean: opt(C_Clean.class),
            run  : opt(C_Run.class),
            debug: opt(C_Debug.class),
            help : opt(C_Help.class),
    ]

    /**
     * 入口函数
     * @param args 参数命令
     */
    static void main(String[] args)
    {
        run(parseCommands(args))
    }

    /**
     * 解析命令
     * @param args 命令参数
     */
    static void run(String[] args)
    {
        loadCommandOptions()
        def commandLineParser = new DefaultParser()
        try
        {
            execute(commandLineParser.parse(options, args))
        } catch (Throwable ignore)
        {
            commandArgs.help.exec(options)
        }
    }

    /**
     * 解析命令
     * @param commandLine 判断当前执行命令
     */
    static void execute(CommandLine cli)
    {

        if (cli.hasOption(getCommandKey(commandArgs.run)))
        {
            commandArgs.run.exec(cli.getOptionValue(getCommandKey(commandArgs.run)))
        }

        if (cli.hasOption(getCommandKey(commandArgs.help)))
        {
            commandArgs.help.exec()
            exit()
        }

    }

    /**
     * 创建{@code Option}参数
     */
    static <T extends GohanOption> GohanOption opt(Class<T> clazz)
    {
        return ClassUtils.newInstance(clazz)
    }

    /** 加载命令行 **/
    static void loadCommandOptions()
    {
        options = new Options()
        commandArgs.each { K, V ->
            options.addOption(V)
        }
    }

    /** 解析命令行 **/
    static String[] parseCommands(String[] args)
    {
        args.eachWithIndex { String entry, int i ->
            def option = commandArgs.get(entry)
            if (option != null)
            {
                args[i] = "-" + args[i]
            }
        }
    }

    /** 只获取longOpt，也就是长命令。为了统一不使用短命令行 **/
    static String getCommandKey(Option hasOpt) { hasOpt.getLongOpt() }

    /** 退出程序 **/
    static void exit()
    {
        System.exit(0)
    }

}
