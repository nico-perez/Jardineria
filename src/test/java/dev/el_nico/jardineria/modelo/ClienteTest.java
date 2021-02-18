package dev.el_nico.jardineria.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.excepciones.ExcepcionFormatoIncorrecto;

public class ClienteTest {

    @Test
    public void LanzaExcepcionConEmailMalformado() {
        Cliente.Builder builder = new Cliente.Builder("nombre", "tlf", "fax", "dir", "ciudad");
        try {
            builder.conEmail("nombre@hotmailes", "1234").buildOrThrow();
            fail();
        } catch (ExcepcionFormatoIncorrecto e) {
            // oke
        } catch (ExcepcionDatoNoValido e) {
            fail();
        } 
        try {
            builder.conEmail("nombrehotmail.es", "1234").buildOrThrow();
            fail();
        } catch (ExcepcionFormatoIncorrecto e) {
            // oke
        } catch (ExcepcionDatoNoValido e) {
            fail();
        }
        try {
            builder.conEmail("@hotmail.es", "1234").buildOrThrow();
            fail();
        } catch (ExcepcionFormatoIncorrecto e) {
            // oke
            assertTrue(true);
        } catch (ExcepcionDatoNoValido e) {
            fail();
        } 
    }

    @Test
    public void AceptaClienteConEmailCorrecto() {
        try {
            new Cliente.Builder("nombre", "tlf", "fax", "dir", "ciudad")
                       .conEmail("un_email@servicio.com", "1234")
                       .buildOrThrow();
            new Cliente.Builder("nombre2", "tlf2", "fax2", "dir2", "ciudad2")
                       .conEmail("djsjfd@fgo.e", "1234")
                       .buildOrThrow();
            new Cliente.Builder("nombre3", "tlf3", "fax3", "dir3", "ciudad3")
                       .conEmail("hola12344@hotmail.com", "1234")
                       .buildOrThrow();
            assertTrue(true);
        } catch (ExcepcionDatoNoValido | ExcepcionFormatoIncorrecto e) {
            fail();
        }
    }

    @Test
    public void LanzaExcepcionConDocumentoMalformado() {
        Cliente.Builder builder = new Cliente.Builder("nombre", "tlf", "fax", "dir", "ciudad");
        try {
            builder.conDocumento(TipoDocumento.DNI, "123456789a").buildOrThrow();
            fail();
        } catch (ExcepcionFormatoIncorrecto e) {
            // oke
        } catch (ExcepcionDatoNoValido e) {
            fail();
        } 
        try {
            builder.conDocumento(TipoDocumento.DNI, "999999999").buildOrThrow();
            fail();
        } catch (ExcepcionFormatoIncorrecto e) {
            // oke
        } catch (ExcepcionDatoNoValido e) {
            fail();
        }
        try {
            builder.conDocumento(TipoDocumento.NIE, "23456789Z").buildOrThrow();
            fail();
        } catch (ExcepcionFormatoIncorrecto e) {
            // oke
        } catch (ExcepcionDatoNoValido e) {
            fail();
        }
        try {
            builder.conDocumento(TipoDocumento.NIE, "holis").buildOrThrow();
            fail();
        } catch (ExcepcionFormatoIncorrecto e) {
            assertTrue(true); // oke
        } catch (ExcepcionDatoNoValido e) {
            fail();
        } 
    }

    @Test
    public void AceptaDocumentoCorrecto() {
        try {
            new Cliente.Builder("nombre", "tlf", "fax", "dir", "ciudad")
                       .conDocumento(TipoDocumento.DNI, "12345678A")
                       .buildOrThrow();
            new Cliente.Builder("nombre2", "tlf2", "fax2", "dir2", "ciudad2")
                       .conDocumento(TipoDocumento.DNI, "22228888I")
                       .buildOrThrow();
            new Cliente.Builder("nombre3", "tlf3", "fax3", "dir3", "ciudad3")
                       .conDocumento(TipoDocumento.NIE, "I0000000I")
                       .buildOrThrow();
            new Cliente.Builder("nombre4", "tlf4", "fax4", "dir4", "ciudad4")
                       .conDocumento(TipoDocumento.NIE, "A1234567B")
                       .buildOrThrow();
            assertTrue(true);
        } catch (ExcepcionDatoNoValido | ExcepcionFormatoIncorrecto e) {
            fail();
        }
    }

    @Test
    public void testAssertEnClienteBuilder() {
        try {
            new Cliente.Builder("txankete", "1243", "123", "1443", "34")
                    .conCodigoPostal("123456789012").buildOrThrow();
                    fail();
        } catch (ExcepcionDatoNoValido | ExcepcionFormatoIncorrecto e) {
            fail();
        } catch (AssertionError e) {
            // oke, el assert ha lanzado exepcion para codigo postal
        }
    }
}
