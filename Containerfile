FROM registry.access.redhat.com/ubi9/openjdk-17:latest

USER root

RUN microdnf install -y git

RUN install -d /home/default/ai-telemetry
COPY . /home/default/ai-telemetry

RUN git clone https://github.com/computate-org/computate-base.git /home/default/computate-base
RUN git clone https://github.com/computate-org/computate-search.git /home/default/computate-search
RUN git clone https://github.com/computate-org/computate-vertx.git /home/default/computate-vertx
RUN git clone https://github.com/computate/ai-telemetry-static.git -b computate /home/default/ai-telemetry-static

WORKDIR /home/default/computate-base
RUN mvn clean install -DskipTests
WORKDIR /home/default/computate-search
RUN mvn clean install -DskipTests
WORKDIR /home/default/computate-vertx
RUN mvn clean install -DskipTests
WORKDIR $HOME/ai-telemetry
RUN mvn clean install -DskipTests

WORKDIR /home/default/ai-telemetry
RUN mvn clean install -DskipTests
RUN mvn dependency:build-classpath -Dmdep.outputFile=/home/default/ai-telemetry/cp.txt -q
CMD java -cp "$(cat /home/default/ai-telemetry/cp.txt):/home/default/ai-telemetry/target/classes" org.mghpcc.aitelemetry.verticle.MainVerticle
