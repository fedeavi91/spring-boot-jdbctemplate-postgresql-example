SHELL := /bin/bash

export HOME_DIR:=../
include ${HOME_DIR}/libs/variables.mk

.PHONY: confirm up start stop clean destroy


confirm:
	@( read -p "$(RED)Are you sure? [y/N]$(RESET): " sure && case "$$sure" in [yY]) true;; *) false;; esac )

up: 
	@${DOCKER_COMPOSE}  up

start:
	@$(DOCKER_COMPOSE) up -d 

stop: 
	@$(DOCKER_COMPOSE) stop 


clean: confirm
	$(DOCKER_COMPOSE) down

destroy: confirm 
	@${DOCKER_COMPOSE} rm -f -s -v 

