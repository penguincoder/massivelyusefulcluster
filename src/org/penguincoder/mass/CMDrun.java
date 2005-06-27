/*
 * Created on Jun 23, 2005
 *
 */
package org.penguincoder.mass;

/**
 * @author Andrew Coleman
 *  
 */
public class CMDrun extends CMD {

    private String hostname;

    private String command;

    private StringBuffer result;

    private final static String lineSeparator = System
            .getProperty ( "line.separator" );

    public CMDrun ( String command, String hostname, ThreadGroup tg,
            String threadName ) {
        super ( tg, threadName );
        this.hostname = hostname;
        this.command = command;
        result = null;
    }

    public void run () {
        String[] toRun;
        if ( System.getProperty ( "os.name" ).toLowerCase ().startsWith (
                "windows" ) ) {
            toRun = new String[4];
            toRun[0] = "plink";
            toRun[1] = "-ssh";
            toRun[2] = this.hostname;
            toRun[3] = this.command;
        } else {
            toRun = new String[3];
            toRun[0] = "ssh";
            toRun[1] = this.hostname;
            toRun[2] = this.command;
        }
        result = Manager.getCommandOutput ( toRun );
    }

    public String toString () {
        if ( result == null )
            return "";
        StringBuffer out = new StringBuffer ( 2048 );
        out.append ( "Command results from '" + this.hostname + "'"
                + lineSeparator );
        out.append ( result.toString () );
        return out.toString () + lineSeparator;
    }
}