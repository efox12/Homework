/**
 * This class contains information and methods for the note object.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #7
 * Icon Sources:
 * Personal icon made by Freepik from www.flaticon.com
 * Work icon made by Freepik from www.flaticon.com
 * Other icon made by Smashicons from www.flaticon.com
 * School icon made by Freepik from www.flaticon.com
 *
 * @author Erik Fox
 * @version v1.0 11/14/17
 */

package com.example.erikfox.notetaker;

import java.io.Serializable;

public class Note implements Serializable{
    private String title;
    private String content;
    private String type;

    /**
     * the default value constructor
     */
    public Note(){
        this.title = "";
        this.content = "";
        this.type = "";
    }

    /**
     * the value constructor
     * @param title the title of the note
     * @param content the content of the note
     * @param type the type of the note
     */
    public Note(String title, String content, String type){
        this.title = title;
        this.content = content;
        this.type = type;
    }

    /**
     * getst the content
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * gets the title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * gets the type
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * sets the content
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * sets the title
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * sets the type
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returnas a string with the fields
     * @return the string
     */
    @Override
    public String toString(){
        String string = "";
        return string + " " + title + " " + content + " " + type;
    }
}
