#!/usr/bin/env groovy
import groovy.json.*

// get list of categories
String base = 'http://api.icndb.com/categories'
String jsonTxt = "$base".toURL().text
println jsonTxt

