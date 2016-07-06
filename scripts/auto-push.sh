#!/bin/bash
git add --all
git commit -m "`TZ=GST-7GDT date '+%Y/%m/%d %H:%M:%S'`"
git push -u origin master
