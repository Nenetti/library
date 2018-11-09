#!/bin/sh

if [ $# = 2 ]; then

  #####################################################################
  #
  # 初期設定
  #
  WS=$(cd $(dirname $0);pwd)
  javas=`find ${WS} -maxdepth 10 -type f -name *.java`
  CLASSPATH=${HOME}/catkin_ws_java/lib
  LIBS=${CLASSPATH}/*
  classes=`find ${WS} -maxdepth 10 -type f -name *.class`

  #####################################################################
  #
  # javacでコンパイル
  #
  echo
  echo "コンパイル開始"
  echo
  javac -classpath ${LIBS}: $javas

  #####################################################################
  #
  # jar変換
  #
  echo
  echo "$2を$1に変換開始"
  echo
  jar cvf $1 $2

  #####################################################################
  #
  # ライブラリーディレクトリーに移動
  #
  cp -f $1 ${CLASSPATH}/$1

  #####################################################################
  #
  # classファイルを削除
  #
  for class in $classes;
  do
    echo "classファイル: ${class}を消去中"
    rm $class
  done

else
  echo
  echo "引数の数が一致しません"
  echo "引数は2つ必要です"
  echo "ex:"
  echo "   $0 <作成ファイル名.jar> <jar化対象フォルダ>"
  echo
fi

