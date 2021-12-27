/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.servicemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Version;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author Utilizador
 */
@Embeddable
public class Script implements ValueObject {

    private String location;

    @Column(length = 1024)
    private String content;

    public Script(String location){
        this.location = location;
        StringBuilder contentBuilder = new StringBuilder();
        try
        {
            Stream<String> stream = Files.lines( Paths.get(location), StandardCharsets.UTF_8);
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
            this.content = contentBuilder.toString().trim();
        }
        catch (UncheckedIOException | IOException e)
        {
        }
    }

    protected Script(){
        //ORM only
    }

    public String content(){
        return content;
    }

    public String toString(){
        return location;
    }

}
