#!/bin/sh
registry=eu.gcr.io
# authenticate to Image Registry
gcloud auth login --cred-file ~/svcacc.json
#gcloud auth print-access-token | podman login -u oauth2accesstoken $registry
gcloud auth print-access-token | docker login -u oauth2accesstoken --password-stdin https://$registry

# build, push and deploy
./gradlew clean build \
    -Dquarkus.container-image.build=true \
    -Dquarkus.container-image.push=true \
    -Dquarkus.kubernetes.deploy=true \
    -Dquarkus.kubernetes.namespace="${CODER_CLOUDOGU_DEPLOY_NAMESPACE}"


#podman push eu.gcr.io/ces-operations-internal/quarkus-hello-world:1.0.0-SNAPSHOT