package org.codehaus.mojo.buildmetadata;

/*
 * The MIT License
 *
 * Copyright (c) 2004, The Codehaus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.Properties;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Set the os properties which contain name, family, version and architecture of the operation system.
 * 
 * <pre>
 *   [propertyPrefix].name
 *   [propertyPrefix].family
 *   [propertyPrefix].version
 *   [propertyPrefix].arch
 * </pre>
 * 
 * Where the propertyPrefix is the string set in the mojo parameter.
 * 
 * @author <a href="codehaus@soebes.de">Karl-Heinz Marbaise</a>
 */
@Mojo( name = "os", defaultPhase = LifecyclePhase.VALIDATE, threadSafe = true )
public class OsInformationMojo
    extends AbstractDefinePropertyMojo
{
    /**
     * Prefix string to use for the set of the operation system properties.
     */
    @Parameter( defaultValue = "build.environment" )
    private String propertyPrefix;

    /* (non-Javadoc)
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute()
        throws MojoExecutionException
    {

        Properties buildEnvironmentProperties = new Properties();
        BuildEnvironmentMetaData buildEnvironment =
            new BuildEnvironmentMetaData( getLog(), getProject(), getSession(), getRuntime(), getDefaultPropertyValue() );

        buildEnvironment.getOperationSystemProperties( buildEnvironmentProperties, propertyPrefix );

        buildEnvironment.defineProjectProperty( buildEnvironmentProperties );

    }
}
