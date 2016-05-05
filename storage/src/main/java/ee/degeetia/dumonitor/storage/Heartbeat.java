/*
 * MIT License
 * Copyright (c) 2016 Estonian Information System Authority (RIA)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ee.degeetia.dumonitor.storage;

import javax.servlet.ServletException;

import ee.degeetia.dumonitor.common.config.BuildProperty;
import ee.degeetia.dumonitor.common.config.PropertyLoader;
import ee.degeetia.dumonitor.common.heartbeat.HeartbeatServlet;

/**
 * Klass realiseerib andmesalvestaja nn. heartbeat teenuse, mis tagastab lihtsa JSON struktuuri teenuse põhiinfoga.
 * Täpsemalt vt vanemklassi kirjeldust.
 */
public class Heartbeat extends HeartbeatServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Servleti initsialiseerimine. Lotakse sisse teenuse konfiguratsioon failist build.properties
	 */
	@Override
	public void init() throws ServletException {
		loadProperties();
	}

	protected void loadProperties() {
		if (BuildProperty.NAME.getValue() == null) {
			PropertyLoader.loadProperties(BuildProperty.class, "build.properties");
		}
	}
}
