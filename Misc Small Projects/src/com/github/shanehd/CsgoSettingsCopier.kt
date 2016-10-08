package com.github.shanehd

import com.github.shanehd.utilities.kotlin.quote
import java.io.File

private const val CSGO_CFG_LOCATION = "730/local/cfg/"

/**
 * @author https://www.github.com/ShaneHD
 * Created by Shane on 08/10/2016.
 */
fun main(args: Array<String>) {
    if(args.size < 2)
        throw IllegalArgumentException("Missing required arguments!")

    val steamDir = File(args[0])
    val user = args[1]
    var blacklist: Array<String?> = arrayOf()

    if(args.size > 2) {
        blacklist = kotlin.arrayOfNulls<String>(args.size - 2)

        for(i in 0..args.size - 3) {
            blacklist[i] = args[i + 2]
        }
    }

    copy(steamDir, user, *blacklist)
    print("Done!")
}

private fun print(line: String) {
    println("> $line")
}

private fun iprint(line: String) {
    print("     $line")
}

private fun copy(steam: File, from: String, vararg blacklist: String?) {
    try {
        print("Attempting to copy settings from user ${from.quote}")

        val base = File(steam, "userdata/")
        val copyFrom = File(base, "$from/$CSGO_CFG_LOCATION")

        for(user in base.listFiles()) {
            if(!user.isDirectory || blacklist.contains(user.name) || user.name == from) {
                print("Skipping ${user.name.quote}")
                continue
            }

            print("Copying files to user ${user.name.quote}")

            for(file in copyFrom.listFiles()) {
                if(file.isDirectory)
                    continue

                try {
                    val new = File(user, "$CSGO_CFG_LOCATION/${file.name}")
                    file.copyTo(new, true)

                    if(!file.canWrite())
                        new.setReadOnly()

                    iprint("Copied file ${file.quote}")
                } catch(e: Throwable) {
                    e.printStackTrace()
                    iprint("Failed...")
                }
            }

            print("Finished with this user")
        }
    } catch(e: Throwable) {
        e.printStackTrace()
    }
}