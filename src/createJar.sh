#!/bin/sh

if [ $# = 1 ]; then

  #####################################################################
  #
  # 初期設定
  #
  WS=$(cd $(dirname $0);pwd)
  javas=`find ${WS} -maxdepth 10 -type f -name *.java`
  CLASSPATH=$JAVA_LIB
  LIBS=${CLASSPATH}/*
  classes=`find ${WS} -maxdepth 10 -type f -name *.class`
  files=`ls -l ${WS} | grep ^d | awk '{print $9}'`

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
  echo "$1に変換開始"
  echo
  jar cvf $1 $files
  echo
  echo "変換完了"
  echo

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
    #echo "classファイル: ${class}を消去中"
    rm $class
  done
  echo
  echo "classファイルを削除"
  echo

else
  echo
  echo "引数の数が一致しません"
  echo "引数は2つ必要です"
  echo "ex:"
  echo "   $0 <作成ファイル名.jar>"
  echo
fi

