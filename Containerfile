FROM registry.access.redhat.com/ubi9/openjdk-17:latest

USER root

RUN microdnf install -y git

RUN install -d /home/default/ai-telemetry
COPY . /home/default/ai-telemetry

RUN git clone https://github.com/computate-org/computate-base.git /home/default/computate-base
RUN git clone https://github.com/computate-org/computate-search.git /home/default/computate-search
RUN git clone https://github.com/computate-org/computate-vertx.git /home/default/computate-vertx
RUN git clone https://github.com/OCP-on-NERC/ai-telemetry-static.git /home/default/ai-telemetry-static

WORKDIR /home/default/computate-base
RUN mvn clean install -DskipTests
WORKDIR /home/default/computate-search
RUN mvn clean install -DskipTests
WORKDIR /home/default/computate-vertx
RUN mvn clean install -DskipTests
WORKDIR $HOME/ai-telemetry
RUN mvn clean install -DskipTests
RUN rm -rf /home/default/computate-base /home/default/computate-search /home/default/computate-vertx

WORKDIR /home/default/ai-telemetry
RUN mvn clean install -DskipTests
RUN mvn dependency:build-classpath -Dmdep.outputFile=/home/default/ai-telemetry/cp.txt -q
CMD java -cp "$(cat /home/default/ai-telemetry/cp.txt):/home/default/ai-telemetry/classes" org.mghpcc.aitelemetry.verticle.MainVerticle
