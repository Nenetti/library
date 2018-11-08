#!/bin/sh

echo
echo "コンパイル開始"
echo

if [ $# = 2 ]; then
  WS=$(cd $(dirname $0);pwd)
  dirs=`find ${WS}/ros -maxdepth 10 -type f -name *.java`
  CLASSPATH=${HOME}/catkin_ws_java/lib
  LIBS=${CLASSPATH}/*
  javac -classpath ${LIBS}: $dirs
  echo
  echo "$2を$1に変換開始"
  echo
  jar cvf $1 $2
  cp -f $1 ${CLASSPATH}/$1
else
  echo
  echo "引数の数が一致しません"
  echo "引数は2つ必要です"
  echo "ex:"
  echo "   $0 <作成ファイル名.jar> <jar化対象フォルダ>"
  echo
fi

