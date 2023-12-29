Create Vault:
    vault server -dev --dev-root-token-id="18456350-7b89-11ed-85e9-a5830358e351"

Add Path:
    set VAULT_ADDR=http://127.0.0.1:8200
    set export VAULT_TOKEN="18456350-7b89-11ed-85e9-a5830358e351"

Create Key/Value:
    vault kv put secret/shopee_local/dev  spring.datasource.username=postgres spring.datasource.password=postgres

