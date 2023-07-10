.DEFAULT_GOAL := help

.PHONY: confirm clean-compose create-network create-jar create-jar-list


confirm:
	@( read -p "$(RED)Are you sure? [y/N]$(RESET): " sure && case "$$sure" in [yY]) true;; *) false;; esac )

clean-compose:
	@[ -f ${COMPOSE_YML} ] && rm ${COMPOSE_YML}; \
	echo "[INFO] Ready to configure a new ${COMPOSE_YML}"

create-network:
	@$(eval NETWORK=$(shell docker network ls | grep -o 'tutorial-net' | awk '{print $$1}')) \
	if [ -z $(NETWORK) ]; then \
		echo "Creating tutorial-net network"; \
		docker network create tutorial-net; \
	else \
		echo "tutorial-net network exits"; \
	fi

create-jar: 
	@for f in tutorial-app-client tutorial-app-server ; do \
		cd ${PROJECT_PATH}/$$f; \
		mvn clean package -DskipTests; \
	done 

create-jar-list:
	@for f in ${LIST} ; do \
		cd ${PROJECT_PATH}/$$f; \
		mvn clean package -DskipTests; \
	done
