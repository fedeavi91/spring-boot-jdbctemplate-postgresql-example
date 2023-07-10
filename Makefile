SHELL := /bin/bash

ROOT_DIR:=$(shell dirname $(realpath $(lastword $(MAKEFILE_LIST))))
include $(ROOT_DIR)/libs/common.mk
include $(ROOT_DIR)/libs/variables.mk
LIST = ""

.PHONY:  up start stop ps clean destroy compile mvn-clean variables update 

variables: ## Print variables
	@echo ${ROOT_DIR} ${DOCKER_COMPOSE} ${COMPOSE_YML} ${PROJECT_PATH} ${NETWORK_NAME}


setup: create-network ## Create soes-sgv2 network if it does not exits

list: ## Show status of running containers
	@docker container ls

services: show-containers ## Show all the available containers you can use

ps: ## Like list but for container in the docker-compose file
	@$(DOCKER_COMPOSE) -f $(COMPOSE_YML) ps

clean: confirm ## Stop and remove all containers, networks..
	$(DOCKER_COMPOSE) -f $(COMPOSE_YML) down

destroy: confirm ## Remove all containers and their volumes (or only one c=<container-name>)
	@${DOCKER_COMPOSE} rm -f -s -v $(c)

ifeq ($(LIST), "")
compile: create-jar
else
compile: create-jar-list 
endif

mvn-clean: clean-jar clean-project-war ## [DEPRECATED] Run a custom mvn-clean on the projects	