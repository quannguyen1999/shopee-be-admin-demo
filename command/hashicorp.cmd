Create Vault:
    vault server -dev --dev-root-token-id="18456350-7b89-11ed-85e9-a5830358e351";

Create Key/Value:
    vault kv put
        secret/shopee_local/prod
        spring.datasource.username=postgres
        spring.datasource.password=postgres;

