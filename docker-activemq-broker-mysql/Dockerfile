FROM java:8

ENV AMQ_VERSION 5.13.4
ENV AMQ_HOME /opt/apache-activemq

RUN curl http://ftp.ps.pl/pub/apache/activemq/$AMQ_VERSION/apache-activemq-$AMQ_VERSION-bin.tar.gz | tar -xz

RUN mv /apache-activemq-$AMQ_VERSION /opt/ && \
    ln -s /opt/apache-activemq-$AMQ_VERSION $AMQ_HOME

EXPOSE 1883 5672 8161 61613 61614 61616

ENTRYPOINT ["/opt/apache-activemq/bin/activemq"]

CMD ["console"]