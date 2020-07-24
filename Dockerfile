FROM java:8

COPY worktime.jar /worktime.jar
COPY work_time.xls /home/work_time.xls

CMD ["--server.port=9527"]

EXPOSE 9527

ENTRYPOINT ["java","-jar","/worktime.jar"]

# docker build -t worktime .
# docker run -d -e TZ="Asia/Shanghai" -p 9527:9527 --name worktime worktime