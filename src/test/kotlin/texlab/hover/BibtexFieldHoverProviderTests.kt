package texlab.hover

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import texlab.WorkspaceBuilder

class BibtexFieldHoverProviderTests {
    @Test
    fun `it should return documentation when hovering over entry types`() {
        WorkspaceBuilder()
                .document("foo.bib", "@article{foo, author = }")
                .hover("foo.bib", 0, 15)
                .let { BibtexFieldHoverProvider.getHover(it) }
                .also { assertNotNull(it) }
    }

    @Test
    fun `it should return null when not hovering over entry types`() {
        WorkspaceBuilder()
                .document("foo.bib", "@article{foo, author = {bar}}")
                .hover("foo.bib", 0, 5)
                .let { BibtexFieldHoverProvider.getHover(it) }
                .also { assertNull(it) }
    }

    @Test
    fun `it should not process LaTeX documents`() {
        WorkspaceBuilder()
                .document("foo.tex", "")
                .hover("foo.tex", 0, 0)
                .let { BibtexFieldHoverProvider.getHover(it) }
                .also { assertNull(it) }
    }
}
