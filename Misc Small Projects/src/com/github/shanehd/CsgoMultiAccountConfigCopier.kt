package com.github.shanehd

import java.io.File
import java.nio.file.Files

/**
 * @author https://www.github.com/ShaneHD
 * Created by Shane on 21/02/2016.
 */

val cfgLoc = "730/local/cfg/"

fun main(args: Array<String>) {
    //The steam user id that you want to copy config from
    val from = "idhere"

    val dir = File("C:/Program Files (x86)/Steam/userdata/")
    val fdir = File(dir, "$from/$cfgLoc/")

    //Any steam ids you don't want to copy to
    val blacklist = arrayOf(from, "anonymous")

    for(user in dir.listFiles()) {
        if (!user.isDirectory || blacklist.contains(user.name))
            continue

        val cdir = File(user, cfgLoc)

        for (file in cdir.listFiles())
            file.delete()

        for (cfg in fdir.listFiles()) {
            Files.copy(cfg.toPath(), File(cdir, cfg.name).toPath())
        }
    }

    println("Done.")
}
