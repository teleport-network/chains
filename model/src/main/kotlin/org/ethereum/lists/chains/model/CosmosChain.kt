package org.ethereum.lists.chains.model

import com.squareup.moshi.JsonClass

data class Currency(
        val coinDenom: String,
        val coinMinimalDenom: String,
        val coinDecimals: Int,
        val coinGeckoId: String,
)

data class BIP44(
        val coinType: Int
)

data class Bech32Config(
        val bech32PrefixAccAddr: String,
        val bech32PrefixAccPub: String,
        val bech32PrefixValAddr: String,
        val bech32PrefixValPub: String,
        val bech32PrefixConsAddr: String,
        val bech32PrefixConsPub: String,
)

data class GasPriceStep(
        val low: Double,
        val average: Double,
        val high: Double,
)

@JsonClass(generateAdapter = true)
data class CosmosChain(
        /**
         * In order to distinct from other chains
         */
        val ecoSystem: String = "COSMOS",

        /**
         * These properties are from Keplr's Suggest Chain API
         */
        val rpc: String, // Address of RPC endpoint of the chain. Default port is 26657
        val rest: String, // Address of REST/API endpoint of the chain. Default port is 1317. Must be enabled in app.toml
        val chainId: String,	// Keplr has a feature which automatically detects when the chain-id has changed, and automatically update to support new chain. However, it should be noted that this functionality will only work when the chain-id follows the {identifier}-{version}(ex.cosmoshub-4) format. Therefore, it is recommended that the chain follows the chain-id format.
        val stakeCurrency: Currency,	// Information on the staking token of the chain
        val walletUrlForStaking: String?,	// https://wallet.keplr.app/#/cosmoshub/stake	The URL for the staking interface frontend for the chain. If you don't have a staking interface built, you can use Lunie Light (opens new window)which supports Keplr.
        val bip44: BIP44,	// BIP44 coin type for address derivation. We recommend using 118(Cosmos Hub) as this would provide good Ledger hardware wallet compatibility by utilizing the Cosmos Ledger app.
        val bech32Config: Bech32Config, 	// Bech32 config using the address prefix of the chain
        val currencies: Array<Currency>,	// (TBD)
        val feeCurrencies: Array<Currency>,	// List of fee tokens accepted by the chain's validator.
        val gasPriceStep: GasPriceStep,	// Three gasPrice values (low, average, high) to estimate transaction fee.
        val features: Array<String>?,
)

