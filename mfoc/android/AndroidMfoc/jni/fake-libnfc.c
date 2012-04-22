/** Fake libnfc implementation
 *
 * See http://code.google.com/p/libnfc/ for libnfc source and docs.
 */
 
#include <nfc/nfc.h>

#define IMPLEMENT_ME ((void)0)

void nfc_perror (const nfc_device *pnd, const char *s)
{
	IMPLEMENT_ME;
}

nfc_device *nfc_open (nfc_context *context, const nfc_connstring connstring)
{
	IMPLEMENT_ME;
}

void nfc_close (nfc_device *pnd)
{
	IMPLEMENT_ME;
}

int nfc_initiator_init (nfc_device *pnd)
{
	IMPLEMENT_ME;
}

int nfc_initiator_select_passive_target (nfc_device *pnd, const nfc_modulation nm, const uint8_t *pbtInitData, const size_t szInitData, nfc_target *pnt)
{
	IMPLEMENT_ME;
}

int nfc_initiator_transceive_bytes (nfc_device *pnd, const uint8_t *pbtTx, const size_t szTx, uint8_t *pbtRx, size_t *pszRx, int timeout)
{
	IMPLEMENT_ME;
}

int nfc_initiator_transceive_bits (nfc_device *pnd, const uint8_t *pbtTx, const size_t szTxBits, const uint8_t *pbtTxPar, uint8_t *pbtRx, uint8_t *pbtRxPar)
{
	IMPLEMENT_ME;
}

void nfc_init(nfc_context *context)
{
	IMPLEMENT_ME;
}

void nfc_exit(nfc_context *context)
{
	IMPLEMENT_ME;
}


int nfc_device_set_property_bool (nfc_device *pnd, const nfc_property property, const bool bEnable)
{
	IMPLEMENT_ME;
}

void iso14443a_crc_append (uint8_t *pbtData, size_t szLen)
{
	IMPLEMENT_ME;
}