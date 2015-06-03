# hadoop
alias hls='hdfs dfs -ls'
alias hrm='hdfs dfs -rm'
alias hpt='hdfs dfs -put'
alias hgt='hdfs dfs -get'
alias hct='hdfs dfs -cat'
alias hmv='hdfs dfs -mv'
alias hcp='hdfs dfs -cp'
alias hmd='hdfs dfs -mkdir'
alias htxt='hdfs dfs -text'
alias hstart="hdfs namenode -format && start-dfs.sh && start-yarn.sh && mr-jobhistory-daemon.sh start historyserver && hdfs dfs -mkdir /user/$USER"
alias hstop="mr-jobhistory-daemon.sh stop historyserver && stop-yarn.sh && stop-dfs.sh"

