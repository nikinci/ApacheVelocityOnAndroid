package com.nikinci.apachevelocityonandroid.velocity.directives;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;


public class TruncateDirective extends Directive {

    public String getName() {
        return "truncate";
    }

    public int getType() {
        return LINE;
    }

    public boolean render(InternalContextAdapter context, Writer writer, Node node) 
            throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {

        //setting default params
        String truncateMe = null;
        int maxLength = 10;
        String suffix = null;
        Boolean truncateAtWord = false;

        //reading params
        if (node.jjtGetChild(0) != null) {
            truncateMe = String.valueOf(node.jjtGetChild(0).value(context));
        }

        if (node.jjtGetChild(1) != null) {
            maxLength = (Integer)node.jjtGetChild(1).value(context);
        }

        if (node.jjtGetChild(2) != null) {
            suffix = String.valueOf(node.jjtGetChild(2).value(context));
        }

        if (node.jjtGetChild(3) != null) {
            truncateAtWord = (Boolean)node.jjtGetChild(3).value(context);
        }

        //truncate and write result to writer
        writer.write(truncate(truncateMe, maxLength, suffix, truncateAtWord));

        return true;

    }

    //does actual truncating (taken directly from DisplayTools)
    public String truncate(String truncateMe, int maxLength, String suffix, boolean truncateAtWord) {
        if (truncateMe == null || maxLength <= 0) {
            return null;
        }

        if (truncateMe.length() <= maxLength) {
            return truncateMe;
        }
        if (suffix == null || maxLength - suffix.length() <= 0) {
            // either no need or no room for suffix
            return truncateMe.substring(0, maxLength);
        }
        if (truncateAtWord) {
            // find the latest space within maxLength
            int lastSpace = truncateMe.substring(0, maxLength - suffix.length() + 1).lastIndexOf(" ");
            if (lastSpace > suffix.length()) {
                return truncateMe.substring(0, lastSpace) + suffix;
            }
        }
        // truncate to exact character and append suffix
        return truncateMe.substring(0, maxLength - suffix.length()) + suffix;

    }

}