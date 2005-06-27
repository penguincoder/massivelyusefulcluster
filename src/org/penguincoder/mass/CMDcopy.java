/*
 * Created on Jun 23, 2005
 *
 */
package org.penguincoder.mass;

import java.util.ArrayList;

/**
 * @author Andrew Coleman
 *  
 */
public class CMDcopy extends CMD {

    private String hostname;

    private ArrayList fileset;

    private String remoteDir;

    private StringBuffer result;

    private final static String lineSeparator = System
            .getProperty ( "line.separator" );

    public CMDcopy ( ArrayList fileset, String remoteDir, String hostname,
            ThreadGroup tg, String threadName ) {
        super ( tg, threadName );
        this.hostname = hostname;
        if ( remoteDir.equals ( "''" ) ) {
            this.remoteDir = "";
        } else {
            this.remoteDir = remoteDir;
        }
        this.fileset = fileset;
        this.result = null;
    }

    public void run () {
        ArrayList toRun = new ArrayList ( 10 );
        if ( System.getProperty ( "os.name" ).toLowerCase ().startsWith (
                "windows" ) ) {
            toRun.add ( "pscp" );
        } else {
            toRun.add ( "scp" );
        }
        toRun.addAll ( this.fileset );
        toRun.add ( this.hostname + ":" + this.remoteDir );
        result = Manager.getCommandOutput ( (String[]) toRun
                .toArray ( new String[0] ) );
    }

    public String toString () {
        if ( result == null )
            return "";
        StringBuffer out = new StringBuffer ( 2048 );
        out.append ( "Secure copy results from '" + this.hostname + "'"
                + lineSeparator );
        out.append ( result.toString () );
        return out.toString () + lineSeparator;
    }
}