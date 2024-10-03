data "github_organization_teams" "all" {
  root_teams_only = true
  summary_only    = true
}

data "azurerm_key_vault" "domain_key_vault" {
  name                = local.kv_domain_name
  resource_group_name = local.kv_domain_resource_group_name
}

#
# Secrets
#
data "azurerm_key_vault_secret" "azuredevops_pat_github_action" {
  name         = "azuredevops-pat-github-action"
  key_vault_id = data.azurerm_key_vault.domain_key_vault.id
}
