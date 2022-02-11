package com.brightspot.tutorial.graphql.demo4;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

/**
 * <p>Markdown FTW!</p>
 *
 * <h1>Heading 1</h1>
 * <h2>Heading 2</h2>
 * <h3>Heading 3</h3>
 * <h4>Heading 4</h4>
 *
 * <hr>
 *
 * <p>Images!</p>
 * <br>
 * <img src="http://brightspot-brightspot.s3.amazonaws.com/bsp/d2/10/35af456b475c80650232a724817a/2.2_gradient%20centered.png" height="150">
 *
 * <hr>
 *
 * <p>Text styles!</p>
 * <p><strong>This sentence is bold.</strong></p>
 * <p><em>This sentence is italic.</em></p>
 * <p><code>// This sentence is code.</code></p>
 * <blockquote>
 * But, please, don't quote me on that!
 * </blockquote>
 *
 * <hr>
 *
 * <p>More Code!</p>
 *
 * <pre>
 * public static void main(String[] args) {
 *     System.out.println("Brightspot is awesome!! 🎉 😎");
 * }
 * </pre>
 * <hr>
 *
 * <p>Lists!</p>
 *
 * <ol>
 *     <li><strong>Agility</strong> - With a headless implementation, back end and front end developers can be working on their parts of the project concurrently.</li>
 *     <li><strong>Flexibility</strong> - Ability to mix and match front-end content offerings, meaning the best user experience can be delivered across every device, channel and touchpoint.</li>
 *     <li><strong>Resiliency</strong> - Supports organizations in future-proofing their businesses by making it easy to continually evolve alongside new technologies.</li>
 *     <li><strong>Reusability</strong> - The modular approach of a headless CMS likewise means you're far less likely to have code that's customized for a specific implementation.</li>
 * </ol>
 *
 */
@ViewInterface
public class Demo4RelationViewModel extends ViewModel<MyFourthGraphQLEndpoint> {

    /**
     * I think you already know everything there is to know about this type and its fields.
     */
    public String getValue() {
        return "Markdown Docs FTW";
    }
}
